package com.jeremydyer.resource.serializers;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.core.woodshop.project.ProjectStep;
import com.jeremydyer.dao.media.PhotoDao;
import com.jeremydyer.dao.media.jdbc.PhotoDaoImpl;
import com.jeremydyer.service.media.impl.PhotoServiceImpl;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 3:38 PM
 */
@Service("projectStepSerializer")
public class ProjectStepSerializer
    extends JsonSerializer<ProjectStep> {

    @Autowired
    PhotoDao photoDao;

    @Override
    public void serialize(ProjectStep value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();

        List<Photo> stepPhotos = photoDao.photosForCategoryAndCategoryId(PhotoServiceImpl.PHOTOCAT_BUILD_STEP,
                value.getProjectStepId());

        jgen.writeObjectField("photos", stepPhotos);
//        jgen.writeObjectField("expiresAt", value.getExpiresAt());
//        jgen.writeNumberField("id", value.getId());
//        jgen.writeObjectField("issuedAt", value.getIssuedAt());
//        jgen.writeObjectField("status", value.getStatus());
//        jgen.writeObjectField("type", value.getType());
        jgen.writeEndObject();
    }

}