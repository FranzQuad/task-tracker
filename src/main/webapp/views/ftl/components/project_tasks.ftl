<div class="row" style="width: 100%;">
    <div style="width: 60%; padding-left: 30px;">


        <ul class="list-group list-group-flush mx-0">
            <#foreach issue in issues>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <h6>${issue.id}</h6>
                    <h6>${issue.name}</h6>
                    <h6>${issue.finished}</h6>
                    <a href="/issue/${issue.id}" class="btn btn-primary">Open</a>
                </li>
            </#foreach>
        </ul>
    </div>
</div>