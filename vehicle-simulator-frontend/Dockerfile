# Use official Node.js image from Docker Hub
FROM node:14-alpine

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire project to the working directory
COPY ./vehicle-simulator-frontend .

# Build the React app
RUN npm run build

# Expose the port that the app runs on
EXPOSE 3000

# Specify the command to run your app
CMD ["npm", "start"]