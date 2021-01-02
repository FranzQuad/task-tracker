<#include '../base.ftl'>

<#macro title>Welcome</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
    <#include '../components/navbar.ftl'>

    <ul class="nav nav-tabs" id="myTab" role="tablist" style="font-size: 30px;">
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="board-tab" data-toggle="tab" href="#board" role="tab" aria-controls="board"
               aria-selected="false">Board</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="tasks-tab" data-toggle="tab" href="#tasks" role="tab" aria-controls="tasks"
               aria-selected="false">Tasks</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="participants-tab" data-toggle="tab" href="#participants" role="tab"
               aria-controls="participants" aria-selected="false">Participants</a>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="board" role="tabpanel" aria-labelledby="board-tab">
            <#include '../components/project_board.ftl'>
        </div>
        <div class="tab-pane fade" id="tasks" role="tabpanel" aria-labelledby="tasks-tab">
            <#include '../components/project_tasks.ftl'>
        </div>
        <div class="tab-pane fade" id="participants" role="tabpanel" aria-labelledby="participants-tab">
            <#include '../components/participants.ftl'>
        </div>
    </div>
</#macro>

<@display_page/>