/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num-delete-dialog.component';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num.service';

describe('Component Tests', () => {
  describe('MGachaRenditionBeforeShootCutInCharacterNum Management Delete Component', () => {
    let comp: MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent>;
    let service: MGachaRenditionBeforeShootCutInCharacterNumService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionBeforeShootCutInCharacterNumService);
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
