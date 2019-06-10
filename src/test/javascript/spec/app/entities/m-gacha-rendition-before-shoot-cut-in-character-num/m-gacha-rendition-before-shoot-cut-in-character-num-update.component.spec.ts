/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num-update.component';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num.service';
import { MGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';

describe('Component Tests', () => {
  describe('MGachaRenditionBeforeShootCutInCharacterNum Management Update Component', () => {
    let comp: MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent>;
    let service: MGachaRenditionBeforeShootCutInCharacterNumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionBeforeShootCutInCharacterNumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionBeforeShootCutInCharacterNum(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionBeforeShootCutInCharacterNum();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
