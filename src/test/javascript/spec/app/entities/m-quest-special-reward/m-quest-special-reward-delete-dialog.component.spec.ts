/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestSpecialRewardDeleteDialogComponent } from 'app/entities/m-quest-special-reward/m-quest-special-reward-delete-dialog.component';
import { MQuestSpecialRewardService } from 'app/entities/m-quest-special-reward/m-quest-special-reward.service';

describe('Component Tests', () => {
  describe('MQuestSpecialReward Management Delete Component', () => {
    let comp: MQuestSpecialRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestSpecialRewardDeleteDialogComponent>;
    let service: MQuestSpecialRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestSpecialRewardDeleteDialogComponent]
      })
        .overrideTemplate(MQuestSpecialRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestSpecialRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestSpecialRewardService);
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
