FROM node:13.12.0-alpine as ui_build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY /ui/package.json ./
COPY /ui/package-lock.json ./
RUN ls ./
RUN npm ci --silent
RUN npm install react-scripts@3.4.1 -g --silent
COPY /ui/ ./
RUN npm run build

FROM tomcat:9
COPY target/rest.war /usr/local/tomcat/webapps/
COPY --from=ui_build /app/build /usr/local/tomcat/webapps
RUN npm run build ./ui/
