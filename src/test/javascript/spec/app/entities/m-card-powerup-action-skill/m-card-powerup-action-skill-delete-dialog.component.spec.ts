/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCardPowerupActionSkillDeleteDialogComponent } from 'app/entities/m-card-powerup-action-skill/m-card-powerup-action-skill-delete-dialog.component';
import { MCardPowerupActionSkillService } from 'app/entities/m-card-powerup-action-skill/m-card-powerup-action-skill.service';

describe('Component Tests', () => {
  describe('MCardPowerupActionSkill Management Delete Component', () => {
    let comp: MCardPowerupActionSkillDeleteDialogComponent;
    let fixture: ComponentFixture<MCardPowerupActionSkillDeleteDialogComponent>;
    let service: MCardPowerupActionSkillService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardPowerupActionSkillDeleteDialogComponent]
      })
        .overrideTemplate(MCardPowerupActionSkillDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardPowerupActionSkillDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardPowerupActionSkillService);
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
