package com.jeremydyer.resource;


/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 1:24 PM
 */
public class ResourceBase {

//    protected final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Context
//    SecurityContext security;
//
//    @Autowired
//    CompanyAccountDao companyAccountDao;
//
//    protected User getLoggedInUser() {
//        Principal principal = security.getUserPrincipal();
//        logger.debug("principal = " + principal.getName());
//        return (User)((UsernamePasswordAuthenticationToken) principal).getDetails();
//    }
//
//    protected CompanyAccount forWhichCompany() throws RenovoServiceException, DaoException {
//        User initiatingUser = getLoggedInUser();
//        if (initiatingUser != null) {
//            // The user no longer carries around a CompanyAccount object.
//            //CompanyAccount sessionAccount = initiatingUser.getCompanyAccount();
//            CompanyAccount sessionAccount = companyAccountDao.find(initiatingUser.getCompanyAccountId());
//
//            //CompanyAccount clonedAccount = new CompanyAccount();
//            //clonedAccount.setCompanyAccountId(initiatingUser.getCompanyAccountId());
//
//            return sessionAccount;
//        } else {
//            throw new RenovoServiceException("failed to retrieve company account");
//        }
//    }
//
//    public static  String getStackTrace(Throwable aThrowable) {
//        final Writer result = new StringWriter();
//        final PrintWriter printWriter = new PrintWriter(result);
//        aThrowable.printStackTrace(printWriter);
//        return result.toString();
//    }
//    public static Map<String,Object> failure(String reason) {
//        return failure(reason, null);
//    }
//
//    public static Map<String,Object> failure(String reason, Exception e) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("reason", reason);
//        if (e!=null) {
//            result.put("e", e.getMessage());
//            // TODO: Should we include stack trace in production responses?
//            // Nice for dev but not sure we want in prod
//
//            result.put("stacktrace", getStackTrace(e));
//        }
//        return result;
//    }
//
//    protected JSONObject transform(JSONObject json, JSONFilter filter) throws JSONException {
//        if (filter == null) {
//            return json;
//        } else {
//            return filter.process(json);
//        }
//    }
//
//
//
//    protected Response buildExceptionResponse(Exception ex, String message) {
//        return buildExceptionResponse(Response.Status.INTERNAL_SERVER_ERROR, ex, message);
//    }
//
//    protected Response buildExceptionResponse(Response.Status status, Exception ex, String message) {
//        try {
//            return Response.status(status).entity(new ObjectMapper().writeValueAsString(failure(message, ex))).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
