/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestTsubasaPointRewardDeleteDialogComponent } from 'app/entities/m-advent-quest-tsubasa-point-reward/m-advent-quest-tsubasa-point-reward-delete-dialog.component';
import { MAdventQuestTsubasaPointRewardService } from 'app/entities/m-advent-quest-tsubasa-point-reward/m-advent-quest-tsubasa-point-reward.service';

describe('Component Tests', () => {
  describe('MAdventQuestTsubasaPointReward Management Delete Component', () => {
    let comp: MAdventQuestTsubasaPointRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MAdventQuestTsubasaPointRewardDeleteDialogComponent>;
    let service: MAdventQuestTsubasaPointRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestTsubasaPointRewardDeleteDialogComponent]
      })
        .overrideTemplate(MAdventQuestTsubasaPointRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestTsubasaPointRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAdventQuestTsubasaPointRewardService);
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
