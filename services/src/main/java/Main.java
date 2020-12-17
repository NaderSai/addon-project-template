

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.picocontainer.Startable;


/**
 * 
 */

/**
 * @author exo
 *
 */
public class Main {
  
  public static void main(String[] args) {
  PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer();
  MyNewStartableService myNewStartableService = (MyNewStartableService) portalContainer.getComponentInstance(MyNewStartableService.class);
  myNewStartableService.start();
  }
}
