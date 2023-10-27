FROM clojure

COPY . /usr/src/app
WORKDIR /usr/src/app

<<<<<<< Updated upstream
CMD ["lein", "run"]
=======
RUN lein uberjar && \
    mv target/uberjar/cinekube-0.1.0-SNAPSHOT-standalone.jar ./cinekube.jar

CMD ["java", "-jar", "cinekube.jar"]
>>>>>>> Stashed changes
