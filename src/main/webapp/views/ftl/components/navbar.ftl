<div fragment="copy">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/myprojects">
            <strong>PineappleTT</strong>
        </a>
        <a href="/myprojects" type="button" class="btn btn-success">My Projects
        </a>
        <button type="button" class="btn btn-warning" style="margin-left: 10px;" data-toggle="modal"
                data-target="#newProject">New Project
        </button>
        <button type="button" class="btn btn-warning" style="margin-left: 2px;" data-toggle="modal"
                data-target="#newTask">New Task
        </button>


        <form action="/logout" method="post" style="margin-left: auto;">
            <div style="font-size: 18px; color: white;">
                ${username}
                <button class="btn btn-outline-danger my-2 my-sm-0" style="margin-left: 10px;" type="submit">Log out</button>
            </div>
        </form>
    </nav>

    <!-- Modal -->
    <div id="newProject" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">New project</h4>
                </div>
                <form action="add-project" method="post">
                    <div class="modal-body">
                        <!-- Name -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Name</span>
                            </div>

                            <input name="name" type="text" class="form-control" aria-label="Sizing example input"
                                   aria-describedby="inputGroup-sizing-default">
                        </div>

                        <!-- Description -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Description</span>
                                </div>
                                <textarea name="description" class="form-control" aria-label="Project Description"></textarea>
                            </div>
                        </div>

                        <!-- Start Date -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="d-flex bd-highlight" style="width: 100%;">
                                <div class="p-2 bd-highlight">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-default">Start</span>
                                    </div>
                                </div>
                                <div class="ml-auto p-2 bd-highlight">
                                    <input name="started" type="date" class="form-control" aria-label="Sizing example input"
                                                                             aria-describedby="inputGroup-sizing-default">
                                </div>
                            </div>
                        </div>

                        <!-- Deadline -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="d-flex bd-highlight" style="width: 100%;">
                                <div class="p-2 bd-highlight">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-default">Deadline</span>
                                    </div>
                                </div>
                                <div class="ml-auto p-2 bd-highlight">
                                    <input name="deadline" type="date" class="form-control" aria-label="Sizing example input"
                                           aria-describedby="inputGroup-sizing-default">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <form action="project/add-issue" method="post">
        <div id="newTask" class="modal fade" role="dialog">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">New Task</h4>
                    </div>
                    <div class="modal-body">

                        <!-- User projects -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Project</span>
                            </div>

                            <select name="projectId" class="form-control">
                                <#foreach project in projects>
                                    <option value="${project.id}">${project.name}</option>
                                </#foreach>
                            </select>
                        </div>

                        <!-- Name -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Name</span>
                            </div>

                            <input name="name" type="text" class="form-control" aria-label="Sizing example input"
                                   aria-describedby="inputGroup-sizing-default">
                        </div>

                        <!-- Description -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Description</span>
                            </div>

                            <textarea name="description" class="form-control" aria-label="Project Description"></textarea>
                        </div>

                        <!-- Assignee -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Assignee</span>
                            </div>

                            <select name="userIds" multiple class="form-control">
                                <#foreach user in users>
                                    <option value="${user.id}">${user.name}</option>
                                </#foreach>
                            </select>
                        </div>

                        <!-- Start Date -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="d-flex bd-highlight" style="width: 100%;">
                                <div class="p-2 bd-highlight">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-default">Start</span>
                                    </div>
                                </div>
                                <div class="ml-auto p-2 bd-highlight">
                                    <input name="started" type="date" class="form-control" aria-label="Sizing example input"
                                           aria-describedby="inputGroup-sizing-default">
                                </div>
                            </div>
                        </div>

                        <!-- Deadline -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="d-flex bd-highlight" style="width: 100%;">
                                <div class="p-2 bd-highlight">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-default">Deadline</span>
                                    </div>
                                </div>
                                <div class="ml-auto p-2 bd-highlight">
                                    <input name="deadline" type="date" class="form-control" aria-label="Sizing example input"
                                           aria-describedby="inputGroup-sizing-default">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!-- Подключаем jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Подключаем плагин Popper -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>

    <!-- Подключаем Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
</div>