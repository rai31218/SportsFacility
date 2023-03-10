# Start with the official Node.js Docker image
FROM node:14.18.1

# Set the working directory to /app
WORKDIR /app

# Copy the source code to the Docker image
COPY . /app

# Install the dependencies
RUN npm install

# Build the Angular application
RUN npm run build

# Expose the port on which the Angular application will run
EXPOSE 4200

# Start the Angular application
CMD ["npm", "start"]
