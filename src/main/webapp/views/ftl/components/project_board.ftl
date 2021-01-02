<div style="width: 60%;">
    <div class="container"
         style="width: 100%; text-align: center; font-weight: bold; margin-top: 10px; color: dimgrey;">
        <div class="row">
            <div class="col-sm">
                To Do
            </div>
            <div class="col-sm">
                In Progress
            </div>
            <div class="col-sm">
                Blocked
            </div>
            <div class="col-sm">
                Closed
            </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <#include '../components/board_tasks.ftl'>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <#include '../components/board_tasks.ftl'>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <#include '../components/board_tasks.ftl'>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <#include '../components/board_tasks.ftl'>
        </div>
    </div>
</div>