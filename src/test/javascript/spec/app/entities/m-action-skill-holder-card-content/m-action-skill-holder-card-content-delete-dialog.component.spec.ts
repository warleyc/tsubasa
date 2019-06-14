/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillHolderCardContentDeleteDialogComponent } from 'app/entities/m-action-skill-holder-card-content/m-action-skill-holder-card-content-delete-dialog.component';
import { MActionSkillHolderCardContentService } from 'app/entities/m-action-skill-holder-card-content/m-action-skill-holder-card-content.service';

describe('Component Tests', () => {
  describe('MActionSkillHolderCardContent Management Delete Component', () => {
    let comp: MActionSkillHolderCardContentDeleteDialogComponent;
    let fixture: ComponentFixture<MActionSkillHolderCardContentDeleteDialogComponent>;
    let service: MActionSkillHolderCardContentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillHolderCardContentDeleteDialogComponent]
      })
        .overrideTemplate(MActionSkillHolderCardContentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionSkillHolderCardContentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionSkillHolderCardContentService);
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
