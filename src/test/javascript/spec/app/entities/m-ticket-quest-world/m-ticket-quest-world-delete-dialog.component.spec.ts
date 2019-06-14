/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestWorldDeleteDialogComponent } from 'app/entities/m-ticket-quest-world/m-ticket-quest-world-delete-dialog.component';
import { MTicketQuestWorldService } from 'app/entities/m-ticket-quest-world/m-ticket-quest-world.service';

describe('Component Tests', () => {
  describe('MTicketQuestWorld Management Delete Component', () => {
    let comp: MTicketQuestWorldDeleteDialogComponent;
    let fixture: ComponentFixture<MTicketQuestWorldDeleteDialogComponent>;
    let service: MTicketQuestWorldService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestWorldDeleteDialogComponent]
      })
        .overrideTemplate(MTicketQuestWorldDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestWorldDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestWorldService);
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
