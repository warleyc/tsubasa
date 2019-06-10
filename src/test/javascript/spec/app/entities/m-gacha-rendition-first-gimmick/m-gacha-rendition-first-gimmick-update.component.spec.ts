/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionFirstGimmickUpdateComponent } from 'app/entities/m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick-update.component';
import { MGachaRenditionFirstGimmickService } from 'app/entities/m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick.service';
import { MGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';

describe('Component Tests', () => {
  describe('MGachaRenditionFirstGimmick Management Update Component', () => {
    let comp: MGachaRenditionFirstGimmickUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionFirstGimmickUpdateComponent>;
    let service: MGachaRenditionFirstGimmickService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionFirstGimmickUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionFirstGimmickUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionFirstGimmickUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionFirstGimmickService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionFirstGimmick(123);
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
        const entity = new MGachaRenditionFirstGimmick();
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
