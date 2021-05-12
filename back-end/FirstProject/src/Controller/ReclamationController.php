<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use App\Repository\UserRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twilio\Rest\Client;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{
    /**
     * @Route("/", name="reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepository
    ,PaginatorInterface $paginator
    ,Request $request
    ): Response
    {
        $data = $reclamationRepository->findAll() ;
        $reclamations = $paginator->paginate(
            $data,
            $request->query->getInt('page' , 1) ,
            6
        );
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamations,
        ]);
    }

    /**
     * @Route("/new", name="reclamation_new", methods={"GET","POST"})
     */
    public function new(Request $request , UserRepository $userRepository): Response
    {
        $reclamation = new Reclamation();
        // get user connected nt a static one
        $reclamation->setIdutilisateur($userRepository->find('12345676'));
        $reclamation->setEtat('en cours');
        $form = $this->createForm(ReclamationType::class, $reclamation ,  array('attr' => [ 'class' => '']));
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();

            $sid = "AC7ab9cc0c4225cc97724ef0070abc67a8";
            $token = "47b6c35f23847f4fbd8cb70c01051cb0";
            $client = new Client($sid, $token);
            $client->messages->create(
                '+216 92 701 035',
                array(
                    'from' => '+16194314835',
                    'body' => "vous avez une reclamation !"
                ));

            return $this->redirectToRoute('prof_reclmataion');
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {

        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="reclamation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reclamation $reclamation): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_index');
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="reclamation_delete", methods={"POST"})
     */
    public function delete(Request $request, Reclamation $reclamation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reclamation_index');
    }
}
