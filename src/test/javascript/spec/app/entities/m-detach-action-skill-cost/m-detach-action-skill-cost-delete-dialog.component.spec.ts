/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDetachActionSkillCostDeleteDialogComponent } from 'app/entities/m-detach-action-skill-cost/m-detach-action-skill-cost-delete-dialog.component';
import { MDetachActionSkillCostService } from 'app/entities/m-detach-action-skill-cost/m-detach-action-skill-cost.service';

describe('Component Tests', () => {
  describe('MDetachActionSkillCost Management Delete Component', () => {
    let comp: MDetachActionSkillCostDeleteDialogComponent;
    let fixture: ComponentFixture<MDetachActionSkillCostDeleteDialogComponent>;
    let service: MDetachActionSkillCostService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDetachActionSkillCostDeleteDialogComponent]
      })
        .overrideTemplate(MDetachActionSkillCostDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDetachActionSkillCostDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDetachActionSkillCostService);
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
