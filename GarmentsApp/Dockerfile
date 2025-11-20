FROM payara/micro:6.2024.5-jdk17

ENV DEPLOY_DIR=/opt/payara/deployments

RUN mkdir ${PAYARA_HOME}/config

COPY --chown=payara:payara domain.xml ${PAYARA_HOME}/config/
COPY --chown=payara:payara mysql.jar ${PAYARA_HOME}/config/

USER payara
WORKDIR ${PAYARA_HOME}

COPY artifact/GarmentsApp.war ${DEPLOY_DIR}/

EXPOSE 8080

CMD ["--addlibs","/opt/payara/config/mysql.jar","--deploymentDir","/opt/payara/deployments","--rootDir","/opt/payara/config","--domainConfig","/opt/payara/config/domain.xml"]