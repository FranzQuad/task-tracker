<div class="container" style="margin-top: 10px;">

    <table class="table" style="width: 100%;">
        <thead class="thead-dark" style="text-align: center;">
        <tr style="height: 56px;">
            <th scope="col" style="width: 5%;">#</th>
            <th scope="col" style="width: 35%;">Name</th>
            <th scope="col" style="width: 20%;">Assignee</th>
            <th scope="col" style="width: 20%;">Deadline</th>
            <th scope="col" style="width: 20%;">Status</th>
            <th scope="col" style="width: 10%;"></th>
        </tr>
        </thead>
        <tbody>
        <#foreach issue in issues>
            <#if issue.issueStatus == "COMPLETE">
                <tr>
                    <th scope="row" style="text-align: center; vertical-align: center; background-color: #6EC679;">${issue.id}</th>
                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">${issue.name}</td>
                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                        <#foreach participant in issue.projectParticipants>
                            ${participant.user.name}, ${participant.projectRole} <br>
                        </#foreach>
                    </td>
                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                        <#if issue.deadline??>
                            ${issue.deadline}
                        <#else>
                            <strong style="color: red">Not specified!</strong>
                        </#if>
                    </td>
                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                        ${issue.issueStatus}
                    </td>
                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                        <a href="/issue/${issue.id}" class="btn btn-primary">Open</a>
                    </td>
                </tr>
                <#elseif issue.issueStatus == "OUTDATED">
                    <tr>
                        <th scope="row" style="text-align: center; vertical-align: center; background-color: #FF6565;">${issue.id}</th>
                        <td style="text-align: center; vertical-align: center; background-color: #FF6565;">${issue.name}</td>
                        <td style="text-align: center; vertical-align: center; background-color: #FF6565;">
                            <#foreach participant in issue.projectParticipants>
                                ${participant.user.name}, ${participant.projectRole} <br>
                            </#foreach>
                        </td>
                        <td style="text-align: center; vertical-align: center; background-color: #FF6565;">
                            <#if issue.deadline??>
                                ${issue.deadline}
                            <#else>
                                <strong style="color: red">Not specified!</strong>
                            </#if>
                        </td>
                        <td style="text-align: center; vertical-align: center; background-color: #FF6565;">
                            ${issue.issueStatus}
                        </td>
                        <td style="text-align: center; vertical-align: center; background-color: #FF6565;">
                            <a href="/issue/${issue.id}" class="btn btn-primary">Open</a>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        <th scope="row" style="text-align: center; vertical-align: center;">${issue.id}</th>
                        <td style="text-align: center; vertical-align: center;">${issue.name}</td>
                        <td style="text-align: center; vertical-align: center;">
                            <#foreach participant in issue.projectParticipants>
                                ${participant.user.name}, ${participant.projectRole} <br>
                            </#foreach>
                        </td>
                        <td style="text-align: center; vertical-align: center;">
                            <#if issue.deadline??>
                                ${issue.deadline}
                            <#else>
                                <strong style="color: red">Not specified!</strong>
                            </#if>
                        </td>
                        <td style="text-align: center; vertical-align: center;">
                            ${issue.issueStatus}
                        </td>
                        <td style="text-align: center; vertical-align: center;">
                            <a href="/issue/${issue.id}" class="btn btn-primary">Open</a>
                        </td>
                    </tr>
            </#if>
        </#foreach>
        </tbody>
    </table>

</div>