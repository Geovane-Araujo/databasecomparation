# Projeto com objetivo de comparação entre Banco de Dados


Iniciei este projeto para fazer um comparativo de tempo de execução de operações de
Inserções, atualizações e Exclusão de uma  grande massa de registros

Nstes utilizei a base de dados publicos de empresas disponibilizado no 

link: http://200.152.38.155/CNPJ/


# Metodos de Teste

Neste processo dispensei o uso do hibernate, nele uso o método convencional de inserção e utilizo também uma lib cliada por mim se encontra no

repositório: https://github.com/Geovane-Araujo/atom-framework

# Bancos Utilizados

Firebird v3.0
PostgresSql v13
Mysql v8
Sqlite v3

# Libs

Para adicionar o AtomFramework baixe a lib neste link: 

https://arquivos.adonaisoft.com.br/libs/atom-framework-2.0.jar 

e depois na lnha de comando adicionar:

mvn install:install-file -Dfile=C:/Libs/atom-framework-2.0.jar -DgroupId=org.atom-framework -DartifactId=atom-framework -Dversion=2.0 -Dpackaging=jar


#Resultado Parcial

![alt text](https://raw.githubusercontent.com/Geovane-Araujo/databasecomparation/main/Comparativo.png)

