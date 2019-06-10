/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillHolderCardCtDeleteDialogComponent } from 'app/entities/m-action-skill-holder-card-ct/m-action-skill-holder-card-ct-delete-dialog.component';
import { MActionSkillHolderCardCtService } from 'app/entities/m-action-skill-holder-card-ct/m-action-skill-holder-card-ct.service';

describe('Component Tests', () => {
  describe('MActionSkillHolderCardCt Management Delete Component', () => {
    let comp: MActionSkillHolderCardCtDeleteDialogComponent;
    let fixture: ComponentFixture<MActionSkillHolderCardCtDeleteDialogComponent>;
    let service: MActionSkillHolderCardCtService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillHolderCardCtDeleteDialogComponent]
      })
        .overrideTemplate(MActionSkillHolderCardCtDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionSkillHolderCardCtDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionSkillHolderCardCtService);
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
