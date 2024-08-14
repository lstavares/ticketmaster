# Usando a imagem oficial do Node.js como base
FROM node:16 as build

LABEL authors="leandrotavares"

# Definindo o diretório de trabalho no contêiner
WORKDIR /app

# Copiando o package.json e package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instalando as dependências do projeto
RUN npm install

# Copiando o restante do código para o diretório de trabalho
COPY . .

# Construindo a aplicação para produção
RUN npm run build

# Expondo a porta na qual a aplicação será executada
EXPOSE 3000

# Comando para iniciar a aplicação
CMD ["npm", "start"]
