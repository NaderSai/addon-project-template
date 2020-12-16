package org.exoplatform.addons.services;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.picocontainer.Startable;
import org.exoplatform.services.log.*;



/**
 * 
 */

/**
 * @author exo
 *
 */
public class MyNewStartableService implements Startable {
  /**
   * The logger.
   */
  private static final Log LOG = ExoLogger.getExoLogger(MyStartableService.class);

  @Override
  public void start() {
    LOG.info("*********************** My new Startable service started !");
    PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer();
    LOG.info("&&&&&&&&&&&& Getting an instance of MyService !");
    MyNewService myNewService = (MyNewService) portalContainer.getComponentInstance(MyNewService.class);
    LOG.info("&&&&&&&&&&&& Calling function call of MyService !");
    myNewService.call();
  }

  @Override
  public void stop() {
    LOG.info("********************* My new Startable service stopped !");
  }
  
  

}


