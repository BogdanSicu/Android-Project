# Android-Project Modificari

### Cum sa incarci ce ai lucrat pe git:
```sh
$ git add .
``` 
Adds the file to your local repository and stages it for commit. To unstage a file, use 'git reset HEAD YOUR-FILE'

```sh
$ git status
```
Displays the state of the working directory and the staging area. It lets you see which changes have been staged, which haven't, and which files aren't being tracked by Gi
```sh
$ git commit -m "Add existing file"
```
Commits the tracked changes and prepares them to be pushed to a remote repository.  To remove this commit and modify the file, use 'git reset --soft HEAD~1' and commit and add the file again.

```sh
$ git status
```

```sh
$ git push origin your-branch
```
Pushes the changes in your local repository up to the remote repository you specified as the origin

Daca esti cu un commit in urma branch-ului
```sh
$ git pull origin your-branch
```
Used to fetch and download content from a remote repository and immediately update the local repository to match that content.


Mai multe detalii: [Aici](https://docs.github.com/en/free-pro-team@latest/github/managing-files-in-a-repository/adding-a-file-to-a-repository-using-the-command-line) 

### Ce avem de facut:
 > Activitate Launch -> Login [ evident :) ]
 
 > Activitate Main -> Pagina Navigation menu
 
> Content: o lista cu conturile atat conturi credit cat si depozite care sa deschida o activitate specifica tipului de cont la click
         - Cont credit:  suma totala datorata, cat ai de platit in momentul deschiderii 
         - Depozit: apare suma din depozit, apare dobanda anuala si dobanda totala obtinuta pe o anumita perioada.
         
> Menu: 
         - sa afiseze toate conturile
         - sa afiseze doar conturile de credit
         - sa afiseze doar depozitele
         - Statistics: un formular unde utilizatorul poate introduce date despre creditul/depozitul lui care o sa ajute si alti utilizatori o activitate unde
                   pot vedea informatii despre diverse banci si ratele/dobanzile acestora.
