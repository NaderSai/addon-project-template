package org.exoplatform.addons.services;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

/**
 * Created by eXo Platform SAS.
 *
 * @author Ali Hamdi <ahamdi@exoplatform.com>
 * @since 13/09/18 15:12
 */

public class MyService {
  /**
   * The logger.
   */
  private static final Log LOG = ExoLogger.getExoLogger(MyService.class);


  public MyService() {
     LOG.info("##################### Simple service started successfully !!");
  }

  public void call() {
    LOG.info("######################  call function invoked !!");
  }
}
