/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStoryResourceMappingDetailComponent } from 'app/entities/m-story-resource-mapping/m-story-resource-mapping-detail.component';
import { MStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';

describe('Component Tests', () => {
  describe('MStoryResourceMapping Management Detail Component', () => {
    let comp: MStoryResourceMappingDetailComponent;
    let fixture: ComponentFixture<MStoryResourceMappingDetailComponent>;
    const route = ({ data: of({ mStoryResourceMapping: new MStoryResourceMapping(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStoryResourceMappingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MStoryResourceMappingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MStoryResourceMappingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mStoryResourceMapping).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
