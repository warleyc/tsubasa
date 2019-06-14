/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestTicketDeleteDialogComponent } from 'app/entities/m-quest-ticket/m-quest-ticket-delete-dialog.component';
import { MQuestTicketService } from 'app/entities/m-quest-ticket/m-quest-ticket.service';

describe('Component Tests', () => {
  describe('MQuestTicket Management Delete Component', () => {
    let comp: MQuestTicketDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestTicketDeleteDialogComponent>;
    let service: MQuestTicketService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestTicketDeleteDialogComponent]
      })
        .overrideTemplate(MQuestTicketDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestTicketDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestTicketService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
