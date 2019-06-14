/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestTsubasaPointRewardDeleteDialogComponent } from 'app/entities/m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward-delete-dialog.component';
import { MGuerillaQuestTsubasaPointRewardService } from 'app/entities/m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward.service';

describe('Component Tests', () => {
  describe('MGuerillaQuestTsubasaPointReward Management Delete Component', () => {
    let comp: MGuerillaQuestTsubasaPointRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MGuerillaQuestTsubasaPointRewardDeleteDialogComponent>;
    let service: MGuerillaQuestTsubasaPointRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestTsubasaPointRewardDeleteDialogComponent]
      })
        .overrideTemplate(MGuerillaQuestTsubasaPointRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestTsubasaPointRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuerillaQuestTsubasaPointRewardService);
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
