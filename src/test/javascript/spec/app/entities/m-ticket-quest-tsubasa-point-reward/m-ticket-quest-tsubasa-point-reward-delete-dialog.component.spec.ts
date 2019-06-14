/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestTsubasaPointRewardDeleteDialogComponent } from 'app/entities/m-ticket-quest-tsubasa-point-reward/m-ticket-quest-tsubasa-point-reward-delete-dialog.component';
import { MTicketQuestTsubasaPointRewardService } from 'app/entities/m-ticket-quest-tsubasa-point-reward/m-ticket-quest-tsubasa-point-reward.service';

describe('Component Tests', () => {
  describe('MTicketQuestTsubasaPointReward Management Delete Component', () => {
    let comp: MTicketQuestTsubasaPointRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MTicketQuestTsubasaPointRewardDeleteDialogComponent>;
    let service: MTicketQuestTsubasaPointRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestTsubasaPointRewardDeleteDialogComponent]
      })
        .overrideTemplate(MTicketQuestTsubasaPointRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestTsubasaPointRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestTsubasaPointRewardService);
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
