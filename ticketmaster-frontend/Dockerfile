# Usando a imagem oficial do Node.js como base
FROM node:16-alpine

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

# Instalando o servidor para servir a aplicação
RUN npm install -g serve

# Expondo a porta na qual a aplicação será executada
EXPOSE 3000

# Comando para iniciar a aplicação
CMD ["serve", "-s", "build"]
