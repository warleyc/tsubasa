/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MInheritActionSkillCostDeleteDialogComponent } from 'app/entities/m-inherit-action-skill-cost/m-inherit-action-skill-cost-delete-dialog.component';
import { MInheritActionSkillCostService } from 'app/entities/m-inherit-action-skill-cost/m-inherit-action-skill-cost.service';

describe('Component Tests', () => {
  describe('MInheritActionSkillCost Management Delete Component', () => {
    let comp: MInheritActionSkillCostDeleteDialogComponent;
    let fixture: ComponentFixture<MInheritActionSkillCostDeleteDialogComponent>;
    let service: MInheritActionSkillCostService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MInheritActionSkillCostDeleteDialogComponent]
      })
        .overrideTemplate(MInheritActionSkillCostDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MInheritActionSkillCostDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MInheritActionSkillCostService);
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
