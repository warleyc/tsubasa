/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAssetTagMappingUpdateComponent } from 'app/entities/m-asset-tag-mapping/m-asset-tag-mapping-update.component';
import { MAssetTagMappingService } from 'app/entities/m-asset-tag-mapping/m-asset-tag-mapping.service';
import { MAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';

describe('Component Tests', () => {
  describe('MAssetTagMapping Management Update Component', () => {
    let comp: MAssetTagMappingUpdateComponent;
    let fixture: ComponentFixture<MAssetTagMappingUpdateComponent>;
    let service: MAssetTagMappingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAssetTagMappingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAssetTagMappingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAssetTagMappingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAssetTagMappingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAssetTagMapping(123);
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
        const entity = new MAssetTagMapping();
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
