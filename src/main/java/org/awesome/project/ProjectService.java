package org.awesome.project;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    @Scheduled(fixedRate = 10000)
    public void getAllPods() {
        try (KubernetesClient k8s = new KubernetesClientBuilder().build()) {
            k8s.apps().deployments().inNamespace("default").list().getItems().forEach(System.out::println);
        }
    }
}
