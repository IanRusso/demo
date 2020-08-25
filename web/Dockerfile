FROM node:13.12.0-alpine as ui_build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY /ui/ ./
RUN npm install --silent
RUN npm run build

FROM tomcat:9
COPY target/rest.war /usr/local/tomcat/webapps/
COPY --from=ui_build /app/dist/ui.war /usr/local/tomcat/webapps/