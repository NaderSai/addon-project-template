package org.exoplatform.addons.services;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;



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
}
