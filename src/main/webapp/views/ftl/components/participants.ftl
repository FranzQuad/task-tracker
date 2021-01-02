<div style="width: 60%; padding-left: 30px;">
    <#foreach participant in project.projectParticipants>
        <ul class="list-group list-group-flush">
            <div class="row">
                <div style="width: 5%; text-align: center; ">
                    <li class="list-group-item list-group-item-action list-group-item-dark">${participant.id}</li>
                </div>
                <div style="width: 95%;">
                    <a href="/task"
                       class="list-group-item list-group-item-action list-group-item-dark">${participant.user.name}</a>
                </div>
                <div style="width: 95%;">
                    <a href="/task"
                       class="list-group-item list-group-item-action list-group-item-dark">${participant.projectRole}</a>
                </div>
            </div>
        </ul>
    </#foreach>
</div>