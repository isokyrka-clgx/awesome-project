package org.awesome.project;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProjectService {

	@Value("${kubeconfig.path:/Users/Ievgenii_Sokyrka/IdeaProjects/stackbooster/awesome-project/k8sconfig}")
	private String kubeConfigFile;

	@Scheduled(fixedRate = 10000)
	public void getAllPods() throws IOException, ApiException {
		//		ApiClient client;
		//		if (StringUtils.isNotBlank(kubeConfigFile) && new File(kubeConfigFile).exists()) { //local
		//			client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigFile))).build();
		//		} else { //cluster
		//			client = ClientBuilder.cluster().build();
		//		}
		//		io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
		//		CoreV1Api api = new CoreV1Api(client);
		//
		//		ModelMapper.addModelMap("", "v1", "Node", "Nodes", V1Node.class, V1NodeList.class);

		try (KubernetesClient k8s = new KubernetesClientBuilder().build()) {
			k8s.apps().deployments().inNamespace("default").list().getItems().forEach(System.out::println);
		}
	}
}
