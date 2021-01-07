<#include '../base.ftl'>

<#macro title>${project.name}</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
    <#include '../components/navbar.ftl'>

    <div class="container-fluid" style="margin-top: 56px;">
        <ul class="nav nav-tabs" id="myTab" role="tablist" style="font-size: 30px; width: 100%;">
            <li class="nav-item" role="presentation">
                <a class="nav-link text-dark" id="board-tab" data-toggle="tab" href="#board" role="tab" aria-controls="board"
                   aria-selected="true">Board</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link text-dark" id="tasks-tab" data-toggle="tab" href="#tasks" role="tab" aria-controls="tasks"
                   aria-selected="false">Tasks</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link text-dark" id="about-tab" data-toggle="tab" href="#about" role="tab" aria-controls="about"
                   aria-selected="false">About</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="board" role="tabpanel" aria-labelledby="board-tab">
                <#include '../components/project_board.ftl'>
            </div>
            <div class="tab-pane fade" id="tasks" role="tabpanel" aria-labelledby="tasks-tab">
                <#include '../components/project_tasks.ftl'>
            </div>
            <div class="tab-pane fade" id="about" role="tabpanel" aria-labelledby="about-tab">
                <#include '../components/project_about.ftl'>
            </div>
        </div>
    </div>

</#macro>

<@display_page/>