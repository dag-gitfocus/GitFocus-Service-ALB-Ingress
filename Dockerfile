FROM openjdk
RUN yum -y update
RUN yum -y install git
RUN mkdir /home/gitfocus
WORKDIR /home/gitfocus
RUN git clone https://github.com/dag-gitfocus/GitFocusService_kube.git
WORKDIR /home/gitfocus/GitFocusService_kube
EXPOSE 8888
ENTRYPOINT ["java","-jar","/home/gitfocus/GitFocusService_kube/GitFocus-Service-0.0.1-SNAPSHOT.jar"]
