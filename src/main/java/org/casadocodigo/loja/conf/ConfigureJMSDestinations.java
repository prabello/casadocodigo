package org.casadocodigo.loja.conf;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(name = "java:/jms/topics/checkoutsTopic", interfaceName = "javax.jms.Topic")
@Singleton
public class ConfigureJMSDestinations {

}
