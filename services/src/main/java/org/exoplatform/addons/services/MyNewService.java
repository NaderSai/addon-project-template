package org.exoplatform.addons.services;

import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.ExoContainer;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import java.util.Date;

/**
 * 
 */

/**
 * @author exo
 *
 */
public class MyNewService {
  /**
   * The logger.
   */
  private static final Log LOG = ExoLogger.getExoLogger(MyService.class);


  public MyNewService() {
    LOG.info("******************************** New Simple service started successfully !!");
  }

  public void call() {
    LOG.info("************************* New  call function invoked !!");
  }

  public void addFavouriteActivity(String title, String link, Date date, String nodeName) throws RepositoryException {
    ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) myContainer.getComponentInstanceOfType(RepositoryService.class);
    Repository repository = repositoryService.getCurrentRepository();
    Session jcrSession = ((ManageableRepository) repository).getSystemSession("collaboration");
    Node root = jcrSession.getRootNode();

    Node newNode = root.addNode(nodeName, "plf:FavoriteActivity");
    Value titleValue = jcrSession.getValueFactory().createValue(title);
    Value linkValue = jcrSession.getValueFactory().createValue(link);
    Value dateValue = jcrSession.getValueFactory().createValue(date.toString());
    newNode.setProperty("plf:title", titleValue);
    newNode.setProperty("plf:link", linkValue);
    newNode.setProperty("plf;date", dateValue);
    jcrSession.save();

  }

  public void listFavoriteActivity() throws RepositoryException {
    ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) myContainer.getComponentInstanceOfType(RepositoryService.class);
    Repository repository = repositoryService.getCurrentRepository();
    Session jcrSession = ((ManageableRepository) repository).getSystemSession("collaboration");
    Node root = jcrSession.getRootNode();

    QueryManager qm = jcrSession.getWorkspace().getQueryManager();
    Query q = qm.createQuery("select * from plf:FavoriteActivity", Query.SQL);
    NodeIterator ni = q.execute().getNodes();
    while (ni.hasNext()) {
      Node iterNode = ni.nextNode();
      System.out.println(iterNode.getName() + " title:"
              + root.getProperty("plf:title").getValue().getString() + " link:"
              + root.getProperty("plf:link").getValue().getString() + " date"
              + root.getProperty("plf:date").getValue().getString());
    }
    jcrSession.save();
  }


  public void removeFavoriteActivity(String title) throws RepositoryException {
    ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) myContainer.getComponentInstanceOfType(RepositoryService.class);
    Repository repository = repositoryService.getCurrentRepository();
    Session jcrSession = ((ManageableRepository) repository).getSystemSession("collaboration");
    Node root = jcrSession.getRootNode();

    QueryManager qm = jcrSession.getWorkspace().getQueryManager();
    Query q = qm.createQuery("select * from plf:FavoriteActivity", Query.SQL);
    NodeIterator ni = q.execute().getNodes();
    while (ni.hasNext()) {
      Node iterNode = ni.nextNode();
      if (iterNode.getProperty("plf:title").getValue().getString() == title) {
        QueryManager qm1 = jcrSession.getWorkspace().getQueryManager();
        Query q1 = qm1.createQuery("delete from plf:FavoriteActivity where title='${title}'", Query.SQL);
      }

    }
    jcrSession.save();


  }

}