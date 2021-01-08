<div class="container-fluid">

    <div class="container">
        <div class="container-fluid d-flex justify-content-between">
            <h3 class="mx-0" style="margin-top: 10px; margin-bottom: 0px;">${project.name}</h3>
            <button type="button" class="btn btn-dark float-right" style="background-color: orange; width: 80px;
                height: 33px; margin-top: 10px;" data-toggle="modal" data-target="#editName">
                Edit
            </button>
        </div>

        <hr>

        <div class="container-fluid">
            <h4>Description</h4>
            <p class="alert alert-info">
                <#if project.description??>
                    ${project.description}
                <#else>
                    It's empty here yet! Please add a project description.
                </#if>
            </p>
            <div class="d-flex justify-content-end">
                <button type="button" class="btn btn-dark" style="background-color: orange; width: 80px;
                height: 33px;" data-toggle="modal" data-target="#editDescription">
                    Edit
                </button>
            </div>
        </div>

        <hr>

        <div class="container-fluid">
            <h4>Dates</h4>
            <div class="d-flex bd-highlight mb-3">
                <div class="p-2 bd-highlight" style="font-weight: bold">Created:</div>
                <div class="ml-auto p-2 bd-highlight">
                    <#if project.created??>
                        ${project.created}
                    <#else>
                        <strong style="color: red">ERROR: create date did not indicate!</strong>
                    </#if>
                </div>
            </div>
            <div class="d-flex bd-highlight mb-3">
                <div class="p-2 bd-highlight" style="font-weight: bold">Start:</div>
                <div class="ml-auto p-2 bd-highlight">
                    <#if project.started??>
                        ${project.started}
                    <#else>
                        Please indicate the start date of the project!
                    </#if>
                </div>
                <div class="p-2 bd-highlight">
                    <button type="button" class="btn btn-dark" style="background-color: orange; width: 80px;
                height: 33px;" data-toggle="modal" data-target="#editStartDate">
                        Edit
                    </button>
                </div>
            </div>
            <div class="d-flex bd-highlight mb-3">
                <div class="p-2 bd-highlight" style="font-weight: bold">Deadline:</div>
                <div class="ml-auto p-2 bd-highlight">
                    <#if project.deadline??>
                        ${project.deadline}
                        <#else>
                            Please indicate the deadline!
                    </#if>
                </div>
                <div class="p-2 bd-highlight">
                    <button type="button" class="btn btn-dark" style="background-color: orange; width: 80px;
                height: 33px;" data-toggle="modal" data-target="#editDeadline">
                        Edit
                    </button>
                </div>
            </div>
            <div class="d-flex bd-highlight mb-3">
                <div class="p-2 bd-highlight" style="font-weight: bold">Finished:</div>
                <div class="ml-auto p-2 bd-highlight">
                    <#if project.finished??>
                        ${project.finished}
                    <#else>
                        The project is not completed!
                    </#if>
                </div>
            </div>
        </div>

        <hr>

        <div class="container-fluid">
            <h4>Participants</h4>
            <table class="table" style="width: 100%;">
                <thead class="thead-dark" style="text-align: center;">
                <tr>
                    <th scope="col" style="width: 10%;">#</th>
                    <th scope="col" style="width: 30%;">Name</th>
                    <th scope="col" style="width: 20%;">Role</th>
                    <th scope="col" style="width: 20%;">Email</th>
                    <th scope="col" style="width: 20%;">
                        <button type="button" class="btn btn-dark" style="background-color: green; width: 80px;
                height: 35px;" data-toggle="modal" data-target="#addParticipant">
                            Add
                        </button>
                    </th>
                </tr>
                </thead>
                <tbody>
                <#foreach projectparticipant in projectparticipants>
                    <tr>
                        <th scope="row" style="text-align: center; vertical-align: center;">${projectparticipant.id}</th>
                        <td style="text-align: center; vertical-align: center;">${projectparticipant.user.name}</td>
                        <td style="text-align: center; vertical-align: center;">${projectparticipant.projectRole}</td>
                        <td style="text-align: center; vertical-align: center;">${projectparticipant.user.email}</td>
                        <td style="text-align: center; vertical-align: center;">
                            <div class="row">
                                <div class="col">
                                    <form action="/project/${project.id}/delete-participant/${projectparticipant.id}" method="post">
                                        <button type="submit" class="btn btn-danger" style="width: 80px;
                    height: 35px;"> <!--data-toggle="modal" data-target="#deleteParticipant"-->Delete</button>

                                        <!-- Delete participant modal window -->
                                        <!--<div id="deleteParticipant${projectparticipant.id}" class="modal fade" role="dialog">
                                            <div class="modal-dialog">
                                                 Modal content
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title"> Are you sure?</h4>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                                        <input type="submit" class="btn btn-danger" value="Yes"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>-->
                                    </form>
                                </div>
                                <div class="col">
                                    <form action="/project/${project.id}/edit-participant/${projectparticipant.id}" method="post">
                                        <button type="button" class="btn btn-dark" style="background-color: orange; width: 80px;
                    height: 35px;" data-toggle="modal" data-target="#editParticipant${projectparticipant.id}">
                                            Edit
                                        </button>

                                        <!-- Edit participant project role modal window -->
                                        <div id="editParticipant${projectparticipant.id}" class="modal fade" role="dialog">
                                            <div class="modal-dialog">
                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Edit Participant ${projectparticipant.user.name}</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <!-- Role -->
                                                        <div class="input-group mb-3" style="width: 100%;">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text" id="inputGroup-sizing-default">Role</span>
                                                            </div>

                                                            <select name="role" class="form-control">
                                                                <#foreach role in roles>
                                                                    <option>${role.name()}</option>
                                                                </#foreach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="d-flex bd-highlight" style="width: 100%;">
                                                            <div class="p-2 bd-highlight" style="font-weight: bold">
                                                            </div>
                                                            <div class="ml-auto p-2 bd-highlight">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                <input type="submit" class="btn btn-default" style="background-color: orange" value="Edit"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </#foreach>
                </tbody>
            </table>
        </div>

        <hr>

        <div class="d-flex bd-highlight" style="width: 100%;">
            <div class="p-2 bd-highlight">
                <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#deleteProject">Delete</button>
            </div>
            <div class="ml-auto p-2 bd-highlight">
                <#if project.finished??>
                    <button disabled type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#completeProject">Complete</button>
                    <#else>
                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#completeProject">Complete</button>
                </#if>
            </div>
        </div>

    </div>

    <!-- Edit project name modal window -->
    <div id="editName" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Description</h4>
                </div>
                <form action="/project/${project.id}/edit-name" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <input name="name" type="text" class="form-control" value="${project.name}">
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

    <!-- Edit project description modal window -->
    <div id="editDescription" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Description</h4>
                </div>
                <form action="/project/${project.id}/edit-description" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <textarea name="description" class="form-control" id="editDescription" rows="5"><#if project.description??>${project.description}</#if>
                            </textarea>
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

    <!-- Edit project start date modal window -->
    <div id="editStartDate" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Start Date</h4>
                </div>
                <form action="/project/${project.id}/edit-startdate" method="post">
                    <div class="modal-body">
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="d-flex bd-highlight" style="width: 100%;">
                                <div class="p-2 bd-highlight">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-default">Start Date</span>
                                    </div>
                                </div>
                                <div class="ml-auto p-2 bd-highlight">
                                    <input name="started" type="date" class="form-control" aria-label="Sizing example input"
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

    <!-- Edit project deadline modal window -->
    <div id="editDeadline" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Deadline</h4>
                </div>
                <form action="/project/${project.id}/edit-deadline" method="post">
                    <div class="modal-body">
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

    <!-- Add participant to the project modal window -->
    <div id="addParticipant" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Add Participant</h4>
                </div>
                <form action="/project/${project.id}/add-participant" method="post">
                    <div class="modal-body">
                        <!-- Email -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
                            </div>

                            <input name="email" type="text" class="form-control" aria-label="Sizing example input"
                                   aria-describedby="inputGroup-sizing-default">
                        </div>

                        <!-- Role -->
                        <div class="input-group mb-3" style="width: 100%;">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Role</span>
                            </div>

                            <select name="role" class="form-control">
                                <#foreach role in roles>
                                    <option>${role.name()}</option>
                                </#foreach>
                            </select>
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



    <!-- Project complete modal window -->
    <div id="completeProject" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <#if allTasksCompleted == true >
                    <div class="modal-header">
                        <h4 class="modal-title"> Are you sure?</h4>
                    </div>
                    <form action="/project/${project.id}/complete" method="post">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                            <input type="submit" class="btn btn-success" value="Yes">
                        </div>
                    </form>
                    <#else>
                        <div class="modal-header">
                            <h4 class="modal-title"> Are you sure?</h4>
                        </div>
                        <div class="modal-body">
                            <h4 style="color: red; text-align: center;">You cannot complete the project because not all tasks are completed!</h4>
                        </div>
                        <form action="/project/${project.id}/complete" method="post">
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                <input disabled type="submit" class="btn btn-success" value="Yes">
                            </div>
                        </form>
                </#if>
            </div>
        </div>
    </div>

    <!-- Project delete modal window -->
    <div id="deleteProject" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"> Are you sure?</h4>
                </div>
                <form action="/project/${project.id}/delete" method="post">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                        <input type="submit" class="btn btn-danger" value="Yes">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>