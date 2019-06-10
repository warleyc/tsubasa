/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern-delete-dialog.component';
import { MGachaRenditionBeforeShootCutInPatternService } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern.service';

describe('Component Tests', () => {
  describe('MGachaRenditionBeforeShootCutInPattern Management Delete Component', () => {
    let comp: MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent>;
    let service: MGachaRenditionBeforeShootCutInPatternService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionBeforeShootCutInPatternService);
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
