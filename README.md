# 1. 초기 설정
java와 관계 없이, 이 코드는 github를 통해 관리되므로, git에 대한 간단한 지식이 필요하다. 먼저 git을 처음 설치하면 유저 설정을 해야함.

> git config --global user.email "e-mail"

> git config --global user.name "이름"

실질적으로 파일을 __git add__ 를 통해 스테이징 하면 __git restore .__ 명령을 통해 모든 커밋 파일을 취소 가능함.

# 2. branch 설정
> git branch "브랜치 이름"
> 
> git switch "브랜치 이름"

위 명령어를 사용하면, 브랜치 생성 및 이동이 가능하다.

만약 두 브랜치를 병합하고 싶다면, __git merge__ 명령어를 통해 가능함.

# 3. github push

기본 브랜치 이름을 master -> main으로 변경하라는 말이 있을 수 있는데, 그때는

> git branch -M main

라고 작성하자. 그리고 원격저장소(github)에 코드를 저장하고 싶다면,

> git push "원격저장소 주소" main //혹은 master

라고 작성하자. 다만 원격저장소 주소를 일일이 입력하는 것은 귀찮으므로,

> git remote add origin "원격저장소 주소"

라고 입력하여, __git push origin main__ 이라고 간결하게 작성할 수 있다.

# 4. github pull

> git pull origin main

push를 하기 전에, 항상 작성하도록 하자. 원격저장소와 로컬저장소에 있는 내용이 다르면, push가 불가능하다.

아무튼, pull 명령어는 push와 반대로 원격저장소의 내용을 로컬저장소로 가져오고, __git clone__ 과 __git fetch__를 동시에 진행하는 명령어라고 생각하면 된다.

# 5. 기타
그 외, __pull request__를 통해 merge를 할 것인지에 대하여 팀원과 검토가 가능하다. 

