package exo.rest.service;

import io.swagger.annotations.ApiParam;
import org.exoplatform.addons.DAO.FavoriteActivityDAO;
import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.IdentityRegistry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Calendar;
import java.util.List;


/**
 * Rest User Service!
 */
@Path("/demo")
@Produces("application/json")
public class RestUserService implements ResourceContainer {

    private FavoriteActivityDAO dao = new FavoriteActivityDAO();

    public RestUserService() {
    }

    public RestUserService(FavoriteActivityDAO dao) {
        this.dao = dao;
    }


    @GET
    @Path("/hello/{name}")
    //@RolesAllowed({"administrators"})
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }

    @GET
    @Path("/listusers/{offset}")
    public Response getListUserName(@Context SecurityContext sc, @PathParam("offset") Integer offset) throws JSONException {
        JSONArray list = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        String groupToCheck = "/platform/administrators";
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);

        if (sc.getUserPrincipal() == null || !this.isMemberOf(sc.getUserPrincipal().getName(), groupToCheck)) {

            jsonObject.put("rights", "NOT-ALLOWED");
            list.put(jsonObject);

        } else {

            OrganizationService organizationService = ExoContainerContext.getCurrentContainer()
                    .getComponentInstanceOfType(OrganizationService.class);
            UserHandler userHandler = organizationService.getUserHandler();
            try {
                ListAccess<User> allUsers = userHandler.findAllUsers();

                if (offset == null || offset < 0)
                    offset = 0;
                int limit = 1000;
                int total = limit + offset;
                int totalUsers = allUsers.getSize();

                if (offset < totalUsers && total > totalUsers) {
                    total = totalUsers;
                }
                User[] users = null;

                for (int i = offset; i < total; i++) {
                    users = allUsers.load(i, 1);
                    jsonObject = new JSONObject();
                    jsonObject.put("username", users[0].getUserName());
                    list.put(jsonObject);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return Response.ok(list.toString(), MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();

    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response create(@ApiParam(value = "entity", required = true) FavoriteActivityEntity entity) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        try {
            entity.setFavouriteDate(Calendar.getInstance());
            dao.addActivity(entity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.ok("OK", MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();

    }

    @GET
    @Path("/show")
    public JSONArray show() throws JSONException {
        JSONArray list = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        String groupToCheck = "/platform/administrators";
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);



            List<FavoriteActivityEntity> allFavoriteActivities = dao.findAllActivity();


            for (int i = 0; i < allFavoriteActivities.size(); i++) {
                jsonObject = new JSONObject();
                jsonObject.put("id", allFavoriteActivities.get(i).getId());
                jsonObject.put("activityTitle", allFavoriteActivities.get(i).getActivityTitle());
                jsonObject.put("targetActivity", allFavoriteActivities.get(i).getTargetActivity());
                jsonObject.put("favoriteDate", allFavoriteActivities.get(i).getFavouriteDate());
                list.put(jsonObject);
            }




        return list;
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id ){
        dao.deleteActivity(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/deleteAll")
    public Response deleteAll(){
        dao.deleteAll();
        return Response.ok().build();
    }

    private boolean isMemberOf(String username, String role) {
        ExoContainer container = ExoContainerContext.getCurrentContainer();
        IdentityRegistry identityRegistry = container.getComponentInstanceOfType(IdentityRegistry.class);
        Identity identity = identityRegistry.getIdentity(username);
        return identity.isMemberOf(role);
    }

}