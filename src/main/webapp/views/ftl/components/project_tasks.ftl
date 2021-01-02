<div class="row" style="width: 100%;">
    <div style="width: 60%; padding-left: 30px;">
        <#foreach issue in issues>
            <ul class="list-group list-group-flush">
                <div class="row">
                    <div style="width: 5%; text-align: center; ">
                        <li class="list-group-item list-group-item-action list-group-item-dark">${issue.id}</li>
                    </div>
                    <div style="width: 95%;">
                        <a href="/task" class="list-group-item list-group-item-action list-group-item-dark">${issue.name}</a>
                    </div>
                </div>
            </ul>
        </#foreach>
    </div>
</div>