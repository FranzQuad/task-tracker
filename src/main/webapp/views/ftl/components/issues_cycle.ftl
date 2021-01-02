<div fragment="copy">

    <ul class="list-group list-group-flush">
        <#foreach issue in issues>
            <#include '../components/card.ftl'>
        </#foreach>
    </ul>

</div>