# Android-Project Modificari

# Cum sa incarci ce ai lucrat pe git:
 - git add . Adds the file to your local repository and stages it for commit. To unstage a file, use 'git reset HEAD YOUR-FILE'
 - git commit -m "Add existing file" Commits the tracked changes and prepares them to be pushed to a remote repository. 
   To remove this commit and modify the file, use 'git reset --soft HEAD~1' and commit and add the file again.
 - git push origin your-branch Pushes the changes in your local repository up to the remote repository you specified as the origin

Eventual mai dai cate un git status intere ele
Daca a mai urcat cineva ceva dupa ce ai clonat tu, intre git commit si git push mai dai un git pull origin nume-branch

Mai multe detalii: https://docs.github.com/en/free-pro-team@latest/github/managing-files-in-a-repository/adding-a-file-to-a-repository-using-the-command-line

# Ce avem de facut:
 - Activitate Launch -> Login [ evident :) ]
 - Activitate Main -> Pagina Navigation menu
    -> Content: o lista cu conturile atat conturi credit cat si depozite care sa deschida o activitate specifica tipului de cont la click
        -> Cont Credit: 
            - suma totala datorata 
            - cat ai de platit in momentul deschiderii 
        -> Depozit: 
            - apare suma din depozit
            - apare dobanda anuala si dobanda totala obtinuta pe o anumita perioada.
    -> Menu: 
        1) sa afiseze doar conturile de credit
        2) sa afiseze doar depozitele
        3) Statistics: un formular unde utilizatorul poate introduce date despre creditul/depozitul lui care o sa ajute si alti utilizatori o activitate unde
                   pot vedea informatii despre diverse banci si ratele/dobanzile acestora.
