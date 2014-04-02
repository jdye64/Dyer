package com.jeremydyer.resource;

import com.makeandbuild.persistence.AbstractPagedRequest;
import com.makeandbuild.persistence.Criteria;
import com.makeandbuild.persistence.ObjectNotFoundException;
import com.makeandbuild.persistence.jdbc.BaseDao;
import com.makeandbuild.persistence.jdbc.PagedResponse;
import com.makeandbuild.persistence.jdbc.SortBy;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 1:26 PM
 */
public abstract class ResourceBase2<T, ID>
        extends ResourceBase {

    private static final Logger logger = LoggerFactory.getLogger(ResourceBase2.class);

    protected abstract BaseDao<T, ID> getDao();
    protected abstract ObjectMapper getObjectMapper();
    protected Class resourceClass;

    public ResourceBase2(Class resourceClass) {
        super();
        this.resourceClass = resourceClass;
    }


    @Path("/{id}")
    @GET
    public Response getOne(@PathParam("id") final ID id) {
        try{
            T model = getDao().find(id);
            return Response.ok().entity(getObjectMapper().writeValueAsString(model)).build();
        }catch(ObjectNotFoundException e){
            return Response.status(400).build();
        }catch(Exception e){
            logger.warn("error with id "+id, e);
            throw new RestClientException("resource with id "+id+" not found");
        }
    }


    @GET
    public Response getList(@Context UriInfo ui) {
        try {
            List<Criteria> criterias = new ArrayList<Criteria>();
            List<SortBy> sortBys = new ArrayList<SortBy>();
            AbstractPagedRequest request = new AbstractPagedRequest();

            MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
            for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
                String key = entry.getKey();
                if ("sortBys".equals(key)){
                    String[] sortyBys = entry.getValue().get(0).split(",");
                    for (String sortyBy : sortyBys) {
                        String[] pair = sortyBy.split(":");
                        String attribute = pair[0];
                        boolean order = pair.length == 0 ? true : Boolean.parseBoolean(pair[1]);
                        sortBys.add(new SortBy(attribute, order));
                    }
                } else if ("pageSize".equals(key)){
                    int pageSize = Integer.parseInt(entry.getValue().get(0));
                    request.setPageSize(pageSize);
                } else if ("page".equals(key)){
                    int page = Integer.parseInt(entry.getValue().get(0));
                    request.setPage(page);
                } else {
                    Criteria criteria = new Criteria(key, entry.getValue().get(0));
                    criterias.add(criteria);
                }
            }
            PagedResponse<T> response = getDao().find(request, criterias, sortBys);
            return Response.ok().entity(getObjectMapper().writeValueAsString(response)).build();
        }catch(Exception e){
            logger.warn("error gettting list", e);
            throw new RestClientException("error getting list");
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(String json) {
        try{
            T model = (T) getObjectMapper().readValue(json, resourceClass);
            model = getDao().create(model);
            return Response.ok().entity(getObjectMapper().writeValueAsString(model)).build();
        }catch(ObjectNotFoundException e){
            return Response.status(400).build();
        }catch(Exception e){
            logger.warn("could not create resource with payload "+json, e);
            throw new RestClientException("unable to create resource");
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String json) {
        try{
            T model = (T) getObjectMapper().readValue(json, resourceClass);
            model = getDao().update(model);
            return Response.ok().entity(getObjectMapper().writeValueAsString(model)).build();
        }catch(ObjectNotFoundException e){
            return Response.status(400).build();
        }catch(Exception e){
            logger.warn("could not update resource with payload "+json, e);
            throw new RestClientException("unable to create resource");
        }
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") final ID id) {
        try{
            getDao().deleteById(id);
            return Response.ok().build();
        }catch(ObjectNotFoundException e){
            return Response.status(400).build();
        }catch(Exception e){
            logger.warn("could not delete resource with id "+id, e);
            throw new RestClientException("could not delete resource with id "+id+" not found");
        }
    }
}