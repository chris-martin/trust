Trust Manifesto
===============

Trust was initially conceived as a way to replace conventional bugtrackers for software development, so we will revisit this use case often to ensure that its needs are met. However, our self-organization goal almost necessitates that the design cannot be strictly tied to any particular domain, and it is not difficult to imagine organic emergent uses.

A system administered by a select few tends to reflect only the interests of management. When the system does not serve as a useful tool to everyone, communication and record-keeping slips into side channels or falls away entirely. Trust is designed to maximize sharing while permitting no one's own interest to stand in the way of anyone else's utility.

As an example of the failure of a conventional bugtracker, consider the following typical scenario.

> Bug: 1 - Status: Accepted<br/>
> Description: The software pizzas when it should hot dog.<br/>
> Comment: I've seen this too, it does pizza sometimes.

> Bug: 2 - Status: Closed (Duplicate)<br/>
> Description: The software hot dogs when it should pizza.<br/>
> Comment: Looks like the same underlying cause as Issue 1.

There are three error reports in this example - bug 1, bug 2, and the comment on bug 1 that agrees with it - and this is not reflected by the result (one open bug with two comments on it).

The users who entered bugs 1 and 2 both took the same action, but now are in different states. The reporter of the second bug is likely alienated from the project, despite having contributed secondary report that may be of some value (at least more helpful than that of the commenter on bug 1).

This tracker's structure is a hindrance because it fails to accurately reflect reality, and it fails to take into consideration that the pieces of information mean different things to different people.

To the reporter (who we can imagine belonging to any number of roles such as OSS user, project sponsor, office manager, or quality control), one's own report is an important entity in itself. The reporter is not apt to care how the report is perceived by the development team in the context of other reports and comments. One sees the underlying software error through the lens of one's own report.

To a developer, a bug is a container for organizing reports and tracking work, and each report may or may not signify a task for which the developer is accountable for addressing (perhaps this depends on the developer's relationship to the stakeholder who submitted the report).

We could take this discussion further, but hopefully by now the point is somewhat illuminated: The importance and most useful way to structure the data in a bugtracker depends on who you are.

Products sometimes to address the problem of differences between users by partitioning them, into role-based groups like "developer" and "customer" and "manager", though the result typically amounts to little more than a few access control lists and some superficial customization to a user interface.

Rather than attempting to provide user-specific views on authoritative data, Trust admits a separate epistemology for each user to support every person's own understanding of the truth. The aim is not to introduce chaos or foster insubordination among employees, but to provide a data store which functions in a useful way despite the chaos and contradiction that is inevitable.

Chaos does exist within organizations that use traditional information systems; it simply goes unrecorded because it cannot be pigeonholed into some idealist relational database schema. As a simple example, consider a bug in a typical tracker with a single "priority" attribute. There is no objective true value to this field. The customer has priorities. The project manager has priorities based on the customer's priorities. The developer is in different ways accountable to both the customer and the project manager, but also to personal judgement. The programmer with hands dirtied in code best knows best whether one bug blocks other or whether a non-functional change is critical. Regardless of corporate policy, individual actions are undertaken by individuals. To transcend primitive information systems, we aggregate all of the inputs that truly feed decisions, and put forth more effort into reflecting reality than into shaping it.

Again I emphasize that the goal is not insubordination, and the goal is to optionally enhance one's own workflow without disrupting for forsaking others'. If the customer insists on a particular structuring of data for their sake, so be it. But that needs not affect what goes on "inside the privacy of one's own office", so to speak. We can envision integration with other tracking systems, so an employee might use Trust personally as an interface on top of the corporate Jira instance, and no one else would even need to notice.

I will now, without justification, present a list of criterion I believe are necessary for the design of such a system, and explain why I call it Trust.

There are no parameters whatsoever to configure when installing a Trust instance. This frees the users from the tyranny of the system administrator and whatever authority to whom the sysadmin must answer. In fact, we will set up a single public Trust installation, and claim that the only reason anyone might want to run their own is to ensure performance, reliability, or privacy. It is from this assumption that I surmise Trust has the capability to expand in utility far beyond the need that spawned it.

One exception to the zero-configuration rule: A server may need to require some central authentication scheme. This is necessary to prevent and detect abusive behavior (like uploading illegal material or an excessive amount of data). An organization conducting secret business may require authentication for privacy as well. However, we will assert that all such considerations can be cared for outside of Trust and insist that they have no impact on the design of Trust itself. Therefore there will be no further mention of this sort of authentication control in this document.

All data in the system is permanent. We accept the cost in performance in exchange for confidence in data integrity and simplicity of design.

It would be desirable for the system to have some of the qualities of a distributed version control system like git; namely, the ability to copy a repo (or pieces of it), make local commits offline, and push them later. It would be desirable, but I cannot yet say whether it is feasible. Or perhaps an offline strategy will be necessary, to ease the burden of computation from a central server.

Although everyone has their own version of the truth, Trust consolidates them with trust. People you don't know have some small influence (this emulates bugtracker features like letting anonymous users enter bug reports and vote on importance). The opinions of your trusted ally at work has value almost as important as your own. Or perhaps equal to your own; you could specify, among some group of trusted people, that the truth of a matter depends merely on who said it last (this emulates an ACL-controlled mutable field in a traditional bugtracker). Or maybe you want to interpret various people's opinions by concatenating them; you care about what both the customer and the manager think, so you want to see them both.

The trust model allows us to defer decisions to others, including who else to trust. So, to visit another bugtracking example, suppose you work on a quality control team that is reponsible for verifying others' work. Your manager has a list of all of your coworkers and will not be satisfied unless someone on the team has signed off on every closed bug. You trust your manager to provide the list of acceptable verifiers, but you don't trust your unreliable coworker Steve, so you provide an overriding assertion that Steve does not count as a verifier. Your view of the system now appropriately reflects your view of reality; although the manager thinks a bug is verified, neither you nor your view in Trust will believe it if the only verifier was Steve.

To have any concept of identity without a centralized authentication scheme, we will be utilizing asymmetric cryptography with a web of trust. All content posted to the system may be paired with a signature. In a basic case, someone within an organization may function as a key authority, but individuals may also share identities offline and trust them transitively within the Trust system.

One cool thing you can do as a result of trust propagation; you could specify that other user ids, restricted to a certain time frame, are yourself. So, supposing your private key was leaked, you could establish a new identity under a new key, and associate it with the old one up until the point where the key was compromised.