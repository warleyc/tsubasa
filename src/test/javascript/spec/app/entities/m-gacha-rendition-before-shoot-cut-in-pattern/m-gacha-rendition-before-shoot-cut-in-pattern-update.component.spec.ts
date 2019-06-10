/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBeforeShootCutInPatternUpdateComponent } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern-update.component';
import { MGachaRenditionBeforeShootCutInPatternService } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern.service';
import { MGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';

describe('Component Tests', () => {
  describe('MGachaRenditionBeforeShootCutInPattern Management Update Component', () => {
    let comp: MGachaRenditionBeforeShootCutInPatternUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionBeforeShootCutInPatternUpdateComponent>;
    let service: MGachaRenditionBeforeShootCutInPatternService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBeforeShootCutInPatternUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionBeforeShootCutInPatternUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionBeforeShootCutInPatternUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionBeforeShootCutInPatternService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionBeforeShootCutInPattern(123);
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
        const entity = new MGachaRenditionBeforeShootCutInPattern();
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
