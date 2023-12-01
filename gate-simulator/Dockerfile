FROM node:lts-alpine3.12
WORKDIR /opt/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999

#FROM node:18-alpine3.17
#WORKDIR /app
#COPY . .
#CMD ["npm", "start"]
#EXPOSE 9999