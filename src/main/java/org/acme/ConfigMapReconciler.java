package org.acme;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;
import org.jboss.logging.Logger;

@ControllerConfiguration(triggerReconcilerOnAllEvents = true, generationAwareEventProcessing = false)
public class ConfigMapReconciler implements Reconciler<ConfigMap> {
    private static final Logger LOG = Logger.getLogger(ConfigMapReconciler.class);

    @Override
    public UpdateControl<ConfigMap> reconcile(ConfigMap resource, Context<ConfigMap> context) throws Exception {
        LOG.infof("ConfigMap %s/%s event", resource.getMetadata().getNamespace(), resource.getMetadata().getName());

        if (context.isPrimaryResourceDeleted()) {
            LOG.infof("deleted");
            return UpdateControl.noUpdate();
        }

        LOG.infof("created/updated");
        return UpdateControl.noUpdate();
    }
}
